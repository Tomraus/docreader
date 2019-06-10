package pl.stasko.tomasz.docreader.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.stasko.tomasz.docreader.model.DocumentInfo;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentInfo, String> {
}
