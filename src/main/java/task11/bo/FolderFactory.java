package task11.bo;

import task11.Utilities;

public class FolderFactory {

  public static Folder getFolderWithUniqueName() {
    return new Folder().withName(Utilities.getUniqueString("Folder"));
  }
}
