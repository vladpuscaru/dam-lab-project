package eu.ase.ro.damapp.network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import eu.ase.ro.damapp.AddPlayerActivity;
import eu.ase.ro.damapp.util.Player;


public class JsonParser {
    public static HttpResponse parseJson(String json) {
        if (json == null) {
            return null;
        }

        try {
            JSONObject object = new JSONObject(json);
            List<Item> goalkeeper = getItemListFromJson(object.getJSONArray("goalkeeper"));
            List<Item> center = getItemListFromJson(object.getJSONArray("center"));
            List<Item> inter = getItemListFromJson(object.getJSONArray("inter"));
            List<Item> winger = getItemListFromJson(object.getJSONArray("winger"));
            return new HttpResponse(goalkeeper, inter, center, winger);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static List<Item> getItemListFromJson(JSONArray array) throws JSONException {
        if (array == null) {
            return null;
        }

        List<Item> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            Item item = getItemFromJson(array.getJSONObject(i));
            if (item != null) {
                list.add(item);
            }
        }
        return list;
    }

    private static Item getItemFromJson(JSONObject object) throws JSONException {
        if (object == null) {
            return null;
        }

        String team = object.getString("team");
        String extraInfo = object.getString("extraInfo");
        PlayerInfo playerInfo = getPlayerInfoFromJson(object.getJSONObject("playerInfo"));

        return new Item(team, extraInfo, playerInfo);
    }

    private static PlayerInfo getPlayerInfoFromJson(JSONObject object) throws JSONException {
        if (object == null) {
            return null;
        }

        String name = object.getString("name");
        Date birthday = null;
        try {
            birthday = new SimpleDateFormat(AddPlayerActivity.DATE_FORMAT, Locale.US).parse(object.getString("birthday"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String favHand = object.getString("favoriteHand");
        return new PlayerInfo(name, birthday, favHand);
    }
}
