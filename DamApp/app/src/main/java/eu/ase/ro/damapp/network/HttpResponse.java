package eu.ase.ro.damapp.network;

import java.util.ArrayList;
import java.util.List;

public class HttpResponse {
    private List<Item> goalkeeper;
    private List<Item> inter;
    private List<Item> center;
    private List<Item> winger;

    public HttpResponse(List<Item> goalkeeper, List<Item> inter, List<Item> center, List<Item> winger) {
        this.goalkeeper = goalkeeper;
        this.inter = inter;
        this.center = center;
        this.winger = winger;
    }

    public List<Item> getGoalkeeper() {
        return goalkeeper;
    }

    public void setGoalkeeper(List<Item> goalkeeper) {
        this.goalkeeper = goalkeeper;
    }

    public List<Item> getInter() {
        return inter;
    }

    public void setInter(List<Item> inter) {
        this.inter = inter;
    }

    public List<Item> getCenter() {
        return center;
    }

    public void setCenter(List<Item> center) {
        this.center = center;
    }

    public List<Item> getWinger() {
        return winger;
    }

    public void setWinger(List<Item> winger) {
        this.winger = winger;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "goalkeeper=" + goalkeeper +
                ", inter=" + inter +
                ", center=" + center +
                ", winger=" + winger +
                '}';
    }
}
