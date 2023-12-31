package projet.blog.blog_authms;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import projet.blog.blog_authms.entities.Utilisateur;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static projet.blog.blog_authms.entities.Role.USER;

@SpringBootTest
class BlogAuthmsApplicationTests {

	@Test
	void contextLoads() {
		Utilisateur user = new Utilisateur(1L,"maysae1","p@$$word" ,"jabbar","mayssae","jabbar.mayssae@gmail.com" ,USER);
		assertEquals(1L, user.getId());
		assertEquals("jabbar", user.getNom());
		assertEquals("mayssae", user.getPrenom());
		assertEquals("jabbar.mayssae@gmail.com", user.getEmail());
		assertEquals("maysae1", user.getUsername());

		assertEquals("p@$$word", user.getPassword());
		assertEquals(USER, user.getRole());

	}


}
