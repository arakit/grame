package jp.keipro2013.grame;


public class PackageData {
    private String name = new String();
    private String date = new String();

    PackageData(){

    }
    PackageData(String d, String s){
        this.name = s;
        this.date = d;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
