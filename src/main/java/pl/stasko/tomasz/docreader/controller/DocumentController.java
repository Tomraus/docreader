package pl.stasko.tomasz.docreader.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import pl.stasko.tomasz.docreader.model.DocumentInfo;
import pl.stasko.tomasz.docreader.service.DocumentStorageService;

import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

@Controller
public class DocumentController {

    @Autowired
    private DocumentStorageService documentStorageService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/error")
    public String error() {
        return "index";
    }

    @PostMapping("/files")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) throws TransformerException, SAXException, IOException {
            documentStorageService.uploadFile(file);
            model.addAttribute("message", "Chosen file has been saved in database");
            return "index";

    }

    @GetMapping("/files")
    public String downloadAllFiles(Model model) {
        List<DocumentInfo> allDocsInfo = documentStorageService.downloadAllFiles();
        model.addAttribute("allDocsInfo", allDocsInfo);
        return "all_docs_info";
    }

}
