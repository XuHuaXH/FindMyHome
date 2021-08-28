package src.FindMyHome.controller;

import lombok.var;
import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@RequestMapping
public class ImageController {
        @RequestMapping(value = "/image/{id}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
        public void getImage(@PathVariable("id") int id, HttpServletResponse response) throws IOException {

            var imgFile = new ClassPathResource("static/PropertyImage/" + id + ".jpg");

            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
        }
}
