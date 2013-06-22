package com.janaka.kitchenslk.entity;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.janaka.kitchenslk.commons.CommonFunctions;
import com.janaka.kitchenslk.util.ApplicationConstants;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: May 23, 2013 - 1:26:39 PM
 * Project	: kitchenslk
 */
@Entity(name = "uploadedFile")
@DynamicInsert(value=true)
@DynamicUpdate(value=true)
@Table(name = "UPLOADED_FILE", uniqueConstraints = @UniqueConstraint(columnNames = { "CONSTRUCTED_FILE_NAME" }))
public class UploadedFile implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private long uploadedFileId;
	private String uploadedFileName;
	private String constructedFileName;	
	private String fileUrl;
	private String physicalFilePath;	
	private String thumbnailPath;
	private int versionId;
    private CommonDomainProperty commanDomainProperty;
    
    
	
	
	@Id
    @SequenceGenerator(name = "idsequence", sequenceName = "uploaded_file_id", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idsequence")
    @Column(name = "UPLOADED_FILE_ID", length = 12)
	public long getUploadedFileId() {
		return uploadedFileId;
	}
	public void setUploadedFileId(long uploadedFileId) {
		this.uploadedFileId = uploadedFileId;
	}

	@Column(name="UPLOADED_FILE_NAME")
	public String getUploadedFileName() {
		return uploadedFileName;
	}
	public void setUploadedFileName(String uploadedFileName) {
		this.uploadedFileName = uploadedFileName;
	}

	@Column(name="CONSTRUCTED_FILE_NAME")
	public String getConstructedFileName() {
		return constructedFileName;
	}
	public void setConstructedFileName(String constructedFileName) {
		this.constructedFileName = constructedFileName;
	}

	@Column(name="FILE_URL")
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	@Column(name="PHYSICAL_FILE_PATH")
	public String getPhysicalFilePath() {
		return physicalFilePath;
	}
	public void setPhysicalFilePath(String physicalFilePath) {
		this.physicalFilePath = physicalFilePath;
	}
	
	
	@Column(name="THUMBNAIL_PATH")
	public String getThumbnailPath() {
		return thumbnailPath;
	}
	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}
	
	@Version
    @Column(name = "VERSION_ID")
    public int getVersionId() {
	return versionId;
    }
    
	public void setVersionId(int versionId) {
	this.versionId = versionId;
    }

    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "creationDate", column = @Column(name = "CREATION_DATE")),
	    @AttributeOverride(name = "lastModifiedUser", column = @Column(name = "LAST_MODIFIED_USER")),
	    @AttributeOverride(name = "lastModifiedDate", column = @Column(name = "LAST_MODIFIED_DATE")) })
    public CommonDomainProperty getCommanDomainProperty() {
	return commanDomainProperty;
    }

    public void setCommanDomainProperty(CommonDomainProperty commanDomainProperty) {
	this.commanDomainProperty = commanDomainProperty;
    }
    
    /**
	 * 
	 */
	public UploadedFile() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param map
	 */
	public UploadedFile(Map<String, String> map) {
		this.fileUrl=map.get(ApplicationConstants.FILE_URL);
		this.physicalFilePath=map.get(ApplicationConstants.PHYSICAL_FILE_PATH);
		this.uploadedFileName=map.get(ApplicationConstants.UPLOADED_FILE_NAME);
		this.constructedFileName=map.get(ApplicationConstants.CONSTRUCTED_FILE_NAME);
		this.commanDomainProperty=CommonFunctions.getCommonDomainPropertyForSavingEntity();
	}
        
    @Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(this.uploadedFileId);
        builder.append(this.constructedFileName);
        return builder.toHashCode();
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof UploadedFile) {
			UploadedFile other = (UploadedFile) obj;
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(this.uploadedFileId, other.uploadedFileId);
            builder.append(this.constructedFileName, other.constructedFileName);
            return builder.isEquals();
        }
        return false;
	}

}
