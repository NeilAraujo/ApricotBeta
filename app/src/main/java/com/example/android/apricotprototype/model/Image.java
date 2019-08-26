package com.example.android.apricotprototype.model;

public class Image {
    private String ImageUrl;
    private int ImageId;

    public Image(String MImageUrl,int MId){
        ImageUrl=MImageUrl;
        ImageId=MId;
    }

    public int getImageId() { return ImageId; }
    public String getImageUrl() { return ImageUrl; }

    public void setImageId(int imageId) { ImageId = imageId; }
    public void setImageUrl(String imageUrl) { ImageUrl = imageUrl;}
}
