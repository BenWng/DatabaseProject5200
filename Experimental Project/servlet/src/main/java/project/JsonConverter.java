package project;

import org.json.simple.JSONObject;

public class JsonConverter {
    public User jsonToUser(JSONObject jsonObject) {
        return new User((Integer) jsonObject.get("id"),
                        (String) jsonObject.get("name"),
                        (String) jsonObject.get("email"),
                        (String) jsonObject.get("role"));

    }

    public JSONObject userToJson(User user) {
        JSONObject obj = new JSONObject();
        obj.put("id", user.getId());
        obj.put("name", user.getName());
        obj.put("email", user.getEmail());
        obj.put("pnumber", user.getRole());

        return obj;
    }
}