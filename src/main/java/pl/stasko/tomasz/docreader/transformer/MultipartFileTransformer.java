package pl.stasko.tomasz.docreader.transformer;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

@Component
public class MultipartFileTransformer {

    public String transform(MultipartFile file, String tranformationResourcePath) throws TransformerException, IOException {
        if (file == null || file.isEmpty()) {
            throw new FileNotFoundException("File not found");
        }
        String fileData = new String(file.getBytes());
        TransformerFactory factory = TransformerFactory.newInstance();
        Source transformation = new StreamSource(ClassLoader.getSystemResourceAsStream(tranformationResourcePath));
        Transformer transformer = factory.newTransformer(transformation);
        Source inputSource = new StreamSource(new StringReader(fileData));
        Writer outputWriter = new StringWriter();
        Result outputResult = new StreamResult(outputWriter);
        transformer.transform(inputSource, outputResult);
        return outputWriter.toString();
    }
}
