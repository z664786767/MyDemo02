package bean;

/**
 * Created by aaa on 15-4-21.
 */
public class First {
    private int pagesize;
    private String subname;
    private String name;
    private int themeid;
    private String pic;
    private String shortdesc;

    public First() {
    }

    public First(int pagesize, String subname, String name, int themeid, String pic, String shortdesc) {
        this.pagesize = pagesize;
        this.subname = subname;
        this.name = name;
        this.themeid = themeid;
        this.pic = pic;
        this.shortdesc = shortdesc;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getThemeid() {
        return themeid;
    }

    public void setThemeid(int themeid) {
        this.themeid = themeid;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public void setShortdesc(String shortdesc) {
        this.shortdesc = shortdesc;
    }
}
