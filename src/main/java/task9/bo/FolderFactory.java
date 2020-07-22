package task9.bo;

import task9.Utilities;

public class FolderFactory {
    public static Folder getFolderWithUniqueName(){
        return new Folder().withName(Utilities.getUniqueString("Folder"));
    }
}
