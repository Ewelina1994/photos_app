package pl.klobut.photos_app.gui;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import pl.klobut.photos_app.model.Image;
import pl.klobut.photos_app.repo.ImageRepo;

import java.util.ArrayList;
import java.util.List;

@Route("gallery")
public class GalleryGui extends VerticalLayout {
    ImageRepo imageRepo;
    List<Image> imagesTable;

    public GalleryGui(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;
        imagesTable= imageRepo.findAll();

        imagesTable.stream().forEach(element -> {
            com.vaadin.flow.component.html.Image image=
                    new com.vaadin.flow.component.html.Image(element.getUrl(), "brak");
            add(image);
        });
    }
}
