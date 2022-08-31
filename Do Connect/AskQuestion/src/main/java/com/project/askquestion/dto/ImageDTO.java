package com.project.askquestion.dto;

/*Author: Rajasree
 * Modified Date :25-08-2022
 * Description : Created dto class for imagedto
 */

public class ImageDTO {
 
	private Long id;
	private String OriginalName;
	private String ContentType;
	private Long Size;
	private String EndUserEmail;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOriginalName() {
		return OriginalName;
	}
	public void setOriginalName(String originalName) {
		OriginalName = originalName;
	}
	public String getContentType() {
		return ContentType;
	}
	public void setContentType(String contentType) {
		ContentType = contentType;
	}
	public Long getSize() {
		return Size;
	}
	public void setSize(Long size) {
		Size = size;
	}
	public String getEndUserEmail() {
		return EndUserEmail;
	}
	public void setEndUserEmail(String endUserEmail) {
		EndUserEmail = endUserEmail;
	}
	
	
}
