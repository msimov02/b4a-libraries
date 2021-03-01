# InfoView

InfoView is a simple and easy to use information view for Android.

![InfoView](https://i.imgur.com/xd0axvh.png)

## Usage
1. Copy all the files from InfoViewAdditionalLibraries and paste them inside your B4A Additional Libraries folder.
2. In your B4A Project navigate to the Libraries Manager and add the InfoView Library And DesignSupport Library. If you want to use custom icons add the XmlLayoutBuilder Library as well.
3. To set an icon to the InfoView:
   1. Go To: https://romannurik.github.io/AndroidAssetStudio/
   2. Choose: Generic Icon Generator
   3. Select An Icon.
   4. Set Trim White Spaces To Trim. (Recommended)
   5. Set Asset Padding To 0dp. (Recommended)
   6. Set Color To Transparent. (Recommended)
   7. Download the ZIP For The generated Icon
   8. Unzip the ZIP file
   9. Go to the res folder inside the ZIP and copy all the Drawable folders.
   10. Go to your B4A Project root and create a folder called: Resource
   11. Place the copied Drawable folders in the Resource folder. 
   12. In your B4A Project in the Application Attributes add the following line:
   ```
   #AdditionalRes: ..\Resource
   ```