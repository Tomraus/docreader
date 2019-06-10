package pl.stasko.tomasz.docreader.test;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import pl.stasko.tomasz.docreader.validator.MultipartFileValidator;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MultipartFileValidatorTest {

    @Test(expected = FileNotFoundException.class)
    public void Should_ThrowFileNotFoundException_When_FileIsNull() throws IOException, SAXException {
        MultipartFile file = null;
        MultipartFileValidator.validate(file, "xsd/document.xsd");
    }

    @Test(expected = FileNotFoundException.class)
    public void Should_ThrowFileNotFoundException_When_FileIsEmpty() throws IOException, SAXException {
        MockMultipartFile file = new MockMultipartFile("file", "test.json", "application/json", "".getBytes());
        MultipartFileValidator.validate(file, "xsd/document.xsd");
    }

    @Test
    public void Should_ReturnTrue_When_SchemaIsCorrect() throws IOException, SAXException {
        MockMultipartFile file = new MockMultipartFile("file", "test.xml", "application/xml", "<?xml version=\"1.0\" encoding=\"utf-8\"?> <epaperRequest> <deviceInfo name=\"Browser\" id=\"test@comp\"> <screenInfo width=\"1280\" height=\"752\" dpi=\"160\" /> <osInfo name=\"Browser\" version=\"1.0\" /> <appInfo> <newspaperName>abb</newspaperName> <version>1.0</version> </appInfo> </deviceInfo> <getPages editionDefId=\"11\" publicationDate=\"2017-06-06\"/> </epaperRequest>".getBytes());
        MultipartFileValidator.validate(file, "xsd/document.xsd");
    }

    @Test(expected = SAXException.class)
    public void Should_ThrowSAXException_When_SchemaIsInCorrect() throws IOException, SAXException {
        MockMultipartFile file = new MockMultipartFile("file", "test.xml", "application/xml", "<?xml version=\"1.0\" encoding=\"utf-8\"?> <epaperRequest> <deviceInfo name=\"Browser\" id=\"test@comp\"> <screenInfo width=\"128s0\" height=\"752\" dpi=\"160\" /> <osInfo name=\"Browser\" version=\"1.0\" /> <appInfo> <newspaperName>abb</newspaperName> <version>1.0</version> </appInfo> </deviceInfo> <getPages editionDefId=\"11\" publicationDate=\"2017-06-06\"/> </epaperRequest>".getBytes());
        MultipartFileValidator.validate(file, "xsd/document.xsd");
    }

}
