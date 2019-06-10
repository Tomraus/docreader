package pl.stasko.tomasz.docreader.validator;

import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;

public class MultipartFileValidator {
    private MultipartFileValidator() {
    }

    public static void validate(MultipartFile file, String schemaResourcePath) throws IOException, SAXException {
        if (file == null || file.isEmpty()) {
            throw new FileNotFoundException("File not found");
        }
        String fileData = new String(file.getBytes());
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(ClassLoader.getSystemResource(schemaResourcePath));
        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(new StringReader(fileData)));
    }
}
