public class Route {
    private String name;
    private String folderFrom;
    private String folderTo;

    public Route(String name, String folderFrom, String folderTo) {
        this.name = name;
        this.folderFrom = folderFrom;
        this.folderTo = folderTo;
    }

    public String getName() {
        return name;
    }

    public String getFolderFrom() {
        return folderFrom;
    }

    public String getFolderTo() {
        return folderTo;
    }
}
