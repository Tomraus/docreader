package pl.stasko.tomasz.docreader.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import pl.stasko.tomasz.docreader.model.DocumentInfo;
import pl.stasko.tomasz.docreader.repository.DocumentRepository;
import pl.stasko.tomasz.docreader.transformer.MultipartFileTransformer;
import pl.stasko.tomasz.docreader.validator.MultipartFileValidator;

import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class DocumentStorageService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private MultipartFileTransformer multipartFileTransformer;

    public void uploadFile(MultipartFile file) throws IOException, SAXException, TransformerException {
        MultipartFileValidator.validate(file, "xsd/document.xsd");
        DocumentInfo documentInfo = extractFileInfo(file);
        documentRepository.save(documentInfo);
    }

    public List<DocumentInfo> downloadAllFiles() {
        return documentRepository.findAll();
    }

    private DocumentInfo extractFileInfo(MultipartFile file) throws TransformerException, IOException {
        String docInfoJson = multipartFileTransformer.transform(file,"xslt/document.xsl");
        ObjectMapper objectMapper = new ObjectMapper();
        DocumentInfo documentInfo = objectMapper.readValue(docInfoJson, DocumentInfo.class);
        documentInfo.setFileName(file.getName());
        documentInfo.setUploadDate(new Date());
        return documentInfo;
    }

}
