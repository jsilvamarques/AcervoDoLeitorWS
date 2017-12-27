
package br.com.acervodoleitorws.repository.book;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.com.acervodoleitorws.model.Book;

@Transactional
public class BookRepositoryImpl implements BookRepositoryQuery{

	@Autowired
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getSimpleBooks() {
		Query query = em.createNativeQuery("select isbn, cover, name from book order by creation_date desc limit 10;");
		return this.mountListSimpleBooks(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getBooks() {
		Query query = em.createNativeQuery("select * from book order by creation_date desc limit 10;", Book.class);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getSimpleBooksByViews() {
		Query query = em.createNativeQuery("select isbn, cover, name, views from book order by views desc limit 10;");
		return this.mountListSimpleBooks(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getSimpleBooksByName(String name) {
		Query query = em.createNativeQuery("select isbn, cover, name from book b where lower(b.name) like :name order by creation_date desc limit 10");
		query.setParameter("name", "%" + name.toLowerCase() + "%");
		return this.mountListSimpleBooks(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getSimpleBooksByCategory(Long uid) {
		Query query = em.createNativeQuery("select b.isbn, b.cover, b.name from book b join book_category bc on b.isbn = bc.isbn_book where b.isbn = bc.isbn_book and bc.uid_category = :uid");
		query.setParameter("uid", uid);
		return this.mountListSimpleBooks(query.getResultList());
	}

	@Override
	public void updateView(String isbn) {
		Query query = em.createNativeQuery("update book set views = views + 1 where isbn = :isbn");
		query.setParameter("isbn", isbn);		
		query.executeUpdate();		
	}
	
	private List<Book> mountListSimpleBooks(List<Object[]> result) {
		List<Book> books = new ArrayList<Book>();
		for (int i = 0; i < result.size(); i++) {
			Object[] resultObject = result.get(i);
			Book book = new Book();
			book.setIsbn(resultObject[0].toString());
			book.setCover(resultObject[1].toString());		
			book.setName(resultObject[2].toString());			
			if(resultObject.length == 4){
				book.setViews(Integer.parseInt(resultObject[3].toString()));
			}
			books.add(book);
		}
		
		return books;
	}

}
