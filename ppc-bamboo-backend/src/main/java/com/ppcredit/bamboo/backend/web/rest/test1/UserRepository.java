package com.ppcredit.bamboo.backend.web.rest.test1;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

@Transactional
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	 
	 
	 @Query("from User u where u.id=:id")
	 User findUser(@Param("id") int id);
}
	
