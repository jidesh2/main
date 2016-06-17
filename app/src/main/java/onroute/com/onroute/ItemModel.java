package onroute.com.onroute;


 public class ItemModel {

     // Getter and Setter model for recycler view items
     private String title;
     private String image;

     public ItemModel(String title, String image) {

         this.title = title;

         this.image = image;
     }

     public String getTitle() {
         return title;
     }


     public String getImage() {
         return image;
     }
 }
