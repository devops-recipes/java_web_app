import org.junit.Assert;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import com.shipfmrsamplejava.helloworld.controller.HelloWorldController;
import com.shipfmrsamplejava.helloworld.controller.HelloWorldController.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

//
// public class SimpleTestNameInput {
//
//   @Test
//   public void testNameInput() {
//     HelloWorldController hello = new HelloWorldController();
//     ModelAndView result = hello.showMessage("Test Name");
//     Assert.assertEquals("Test Name", result.name);
//    }
//
//    @Test
//    public void testNoNameInput() {
//      HelloWorldController hello = new HelloWorldController();
//      ModelAndView result = hello.showMessage("");
//      Assert.assertEquals("World", result.name);
//     }
// }

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
// @WebAppConfiguration
@SpringBootTest
public class ControllerTest {

  @Autowired
   private HelloWorldController helloWorldController;

  @InjectMocks
  HelloWorldController controller;

  MockMvc mockMvc;

  @Before
  public void setUp() throws Exception {
      MockitoAnnotations.initMocks(this);
      mockMvc = MockMvcBuilders.standaloneSetup(controller)
              .build();
  }

  @Test
  public void testNameInput() throws Exception {
      this.mockMvc.perform(get("/hello")
        .param("name", "Tee")
        .accept(MediaType.TEXT_HTML)
      ) .andExpect(status().isOk())
        .andExpect(view().name("helloworld"))
        .andExpect(model().attribute("name", "Tee"));
  }
  @Test
  public void testNullNameInput() throws Exception {
      this.mockMvc.perform(get("/hello")
        .param("name", "")
        .accept(MediaType.TEXT_HTML)
      ) .andExpect(status().isOk())
        .andExpect(view().name("helloworld"))
        .andExpect(model().attribute("name", "World"));
  }
}
