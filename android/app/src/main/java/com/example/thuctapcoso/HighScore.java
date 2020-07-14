package com.example.thuctapcoso;

public class HighScore {
    String Ten;
    int Diem;

    public HighScore(String ten, int diem) {
        Ten = ten;
        Diem = diem;
    }

    public HighScore() {

    }


    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public int getDiem() {
        return Diem;
    }

    public void setDiem(int diem) {
        Diem = diem;
    }

}
