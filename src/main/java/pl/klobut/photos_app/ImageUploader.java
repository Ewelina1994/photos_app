package pl.klobut.photos_app;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.klobut.photos_app.model.Image;
import pl.klobut.photos_app.repo.ImageRepo;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class ImageUploader {
    private Cloudinary cloudinary;
    private ImageRepo imageRepo;

    @Autowired
    public ImageUploader(ImageRepo imageRepo) {
        this.imageRepo=imageRepo;
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dxqm2xj8g",
                "api_key", "372873781864829",
                "api_secret", "32gffcW9iwD6l4c4si4xax9-6kY"));
    }

    public String uploadFileAndSaveDb(String path) {
        File file = new File(path);
        System.out.print("path: "+path);
        Map uploadResult = null;
        try {
            uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            imageRepo.save(new Image(uploadResult.get("url").toString()));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print("Błąd, nie zapis: "+path);
        }
        return uploadResult.get("url").toString();
    }

}
