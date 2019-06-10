package pl.stasko.tomasz.docreader.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "documents")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DocumentInfo {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Column(name = "newspaper_name")
    private String newspaperName;
    @Column(name = "width")
    private Integer width;
    @Column(name = "height")
    private Integer height;
    @Column(name = "dpi")
    private Integer dpi;
    @Column(name = "upload_date")
    private Date uploadDate;
    @Column(name = "filename")
    private String fileName;

}
