package sample.mainServer;

public class Information {
    public String name;
    public String deptName;
    public NetworkUtil nc;
    public Information(String name, String deptName, NetworkUtil nc){
        this.name = name;
        this.deptName = deptName;
        this.nc = nc;
    }
}
