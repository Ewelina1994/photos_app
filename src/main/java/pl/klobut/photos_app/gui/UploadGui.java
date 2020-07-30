package pl.klobut.photos_app.gui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Route;
import pl.klobut.photos_app.ImageUploader;

import java.io.File;
import java.io.InputStream;


@Route("upload")
public class UploadGui extends VerticalLayout {

    private ImageUploader imageUploader;


    public UploadGui(ImageUploader imageUploader) {
        this.imageUploader=imageUploader;

        Label label= new Label();
        TextField textField= new TextField();
        Button button = new Button("upload");

//        MemoryBuffer buffer = new MemoryBuffer();
//        Upload upload = new Upload(buffer);
//
//        upload.addSucceededListener(event -> {
//
//            String file = imageUploader.uploadFileAndSaveDb(event.getSource()+event.getFileName());
//            Image image = new Image(file, "brak obrazka :(");
//            label.setText("Udało się wrzucić obrazek");
//            add(label);
//            add(image);
//
//            Component component = createComponent(event.getMIMEType(),
//                    event.getFileName(), buffer.getInputStream());
//            showOutput(event.getFileName(), component, output);
//        });

        button.addClickListener(clickEvent ->
        {
            String file = imageUploader.uploadFileAndSaveDb(textField.getValue());
            Image image = new Image(file, "brak obrazka :(");
            label.setText("Udało się wrzucić obrazek");
            add(label);
            add(image);
        });
        add(textField);
        add(button);

    }
}
