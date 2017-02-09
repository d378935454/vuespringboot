package com.ppcredit.bamboo.backend.web.rest.test1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ppcredit.bamboo.backend.BambooBackendserviceApp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BambooBackendserviceApp.class)
public class ApplicationTests {
	
	@Inject
	private UserRepository userRepository;
	
	@PersistenceContext
	private EntityManager  em;
	
	
	@Before
    public void initTest() {
		
    }
	
	@Test
	public void test() throws Exception {
        // 创建1条记录
		User user = new User();
        user.setName("Jay");
        user.setPassword("123456");
        user.setBirthday("2008-08-08");
        userRepository.save(user);
        System.out.println(">>>>>>>>>>>>>>>>>junit end");
    }
	
	@Test
	public void testquery () throws Exception{
		//查询记录
		List<User> userList = new ArrayList<User>();
		Long i = userRepository.count();
		userList = new ArrayList<User>((Collection<? extends User>) userRepository.findAll());
		System.out.println("user count is : " + i);
		if (userList.size() > 0)
			System.out.println("user birthday is : " + userList.get(0).getBirthday());
		
		
	}
	
	@SuppressWarnings({ "unchecked" })
	@Test
	public void testMix () throws Exception{
		String sql = "SELECT * from user";

		Query query = em.createNativeQuery(sql, User.class);
		List<User> userList = query.getResultList();
		if (em != null) {
			em.close();
		}
		System.out.println("user count is "+ userList.size());
	}
	

	
	
}
