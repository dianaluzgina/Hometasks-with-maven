package task7.bo;

import task7.tests.Utilities;

public class FolderFactory {
    public static Folder getFolderWithUniqueName(){
        return new Folder().withName(Utilities.getUniqueString("Folder"));
    }
}
