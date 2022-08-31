package com.project.askquestion.dto;

/*Author: Rajasree
 * Modified Date :25-08-2022
 * Description : Created dto class for imageuploadresponsedto
 */

public class ImageUploadResponse {
    private String message;

    public ImageUploadResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
