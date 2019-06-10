package pl.stasko.tomasz.docreader.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;
import pl.stasko.tomasz.docreader.transformer.MultipartFileTransformer;

import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.io.IOException;

@RunWith(SpringRunner.class)
public class MultipartTransformerTest {

    @Autowired
    MultipartFileTransformer multipartFileTransformer;

    @TestConfiguration
    static class MultipartFileTransformerConfiguration {
        @Bean
        public MultipartFileTransformer multipartFileTransformer() {
            return new MultipartFileTransformer();
        }
    }

    @Test(expected = FileNotFoundException.class)
    public void Should_ThrowFileNotFoundException_When_FileIsNull() throws IOException, TransformerException {
        MultipartFile file = null;
        multipartFileTransformer.transform(file, "xslt/document.xsl");
    }

    @Test(expected = FileNotFoundException.class)
    public void Should_ThrowFileNotFoundException_When_FileIsEmpty() throws IOException, TransformerException {
        MockMultipartFile file = new MockMultipartFile("file", "test.json", "application/json", "".getBytes());
        multipartFileTransformer.transform(file, "xslt/document.xsl");
    }

    @Test
    public void Should_ReturnTrue_When_TransformationIsCorrect() throws IOException, TransformerException {
        String expected = "{\"width\":1280,\"height\":752,\"dpi\":160,\"newspaperName\":\"abb\"}";
        MockMultipartFile file = new MockMultipartFile("file", "test.xml", "application/xml", "<?xml version=\"1.0\" encoding=\"utf-8\"?> <epaperRequest> <deviceInfo name=\"Browser\" id=\"test@comp\"> <screenInfo width=\"1280\" height=\"752\" dpi=\"160\" /> <osInfo name=\"Browser\" version=\"1.0\" /> <appInfo> <newspaperName>abb</newspaperName> <version>1.0</version> </appInfo> </deviceInfo> <getPages editionDefId=\"11\" publicationDate=\"2017-06-06\"/> </epaperRequest>".getBytes());
        String actual = multipartFileTransformer.transform(file, "xslt/document.xsl");
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = TransformerException.class)
    public void Should_ThrowTransformerException_When_TransformationIsInCorrect() throws IOException, TransformerException {
        MockMultipartFile file = new MockMultipartFile("file", "test.xml", "application/xml", "<?xml version=\"1.0\" encoding=\"utf-8\"?> <badRequest> <deviceInfo name=\"Browser\" id=\"test@comp\"> <screenInfo widsth=\"128s0\" height=\"752\" dpi=\"160\" /> <osInfo name=\"Browser\" version=\"1.0\" /> <appInfo> <newspaperName>abb</newspaperName> <version>1.0</version> </appInfo> </deviceInfo> <getPages editionDefId=\"11\" publicationDate=\"2017-06-06\"/> </epaperRequest>".getBytes());
        multipartFileTransformer.transform(file, "xslt/document.xsl");
    }
}
