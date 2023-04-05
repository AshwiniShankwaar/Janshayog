package com.BitGeekTalks.JanShayog.UserRegistrationAndLogin.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "IdProve")
@Transactional
public class IdProve {
    @Id
    @SequenceGenerator(
            name = "IdProve_seq",
            sequenceName = "IdProve_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "IdProve_seq"
    )
    private long idSerialNo;
    @Column(name = "FileType")
    @NotBlank
    private String fileType;
    @Lob
    @Column(name = "fileData")
    private byte[] fileData;

    @Override
    public String toString() {
        return "IdProve{" +
                "idSerialNo=" + idSerialNo +
                ", fileType='" + fileType + '\'' +
                '}';
    }

    public long getIdSerialNo() {
        return idSerialNo;
    }

    public void setIdSerialNo(long idSerialNo) {
        this.idSerialNo = idSerialNo;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }


    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public IdProve() {
    }

    public IdProve(long idSerialNo, String fileType, byte[] fileData) {
        this.idSerialNo = idSerialNo;
        this.fileType = fileType;
        this.fileData = fileData;
    }
}
