package task11.bo;

public class Folder {

  private String name;

  public String getName() {
    return name;
  }

  public Folder withName(String name) {
    this.name = name;
    return this;
  }
}
