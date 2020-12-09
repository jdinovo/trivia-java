package form;

import java.util.*;

public class CategoryChoice {
    private static final Map<String, List<String>> categoryModel = new HashMap<>();

    public static Map<String, List<String>> getCategoryModel() {
        categoryModel.put("Science", Arrays.asList("Mathematics", "Computers", "Nature", "Gadgets"));
        categoryModel.put("Entertainment", Arrays.asList("Film", "Video Games", "Books", "Music",
                "Musicals & Theatres", "Cartoon & Animations", "Comics",
                "Television", "Board Games"));
        categoryModel.put("General Knowledge", Collections.emptyList());
        categoryModel.put("Geography", Collections.emptyList());
        categoryModel.put("Vehicles", Collections.emptyList());
        categoryModel.put("Politics", Collections.emptyList());
        categoryModel.put("History", Collections.emptyList());
        categoryModel.put("Sports", Collections.emptyList());
        categoryModel.put("Mythology", Collections.emptyList());
        categoryModel.put("Celebrities", Collections.emptyList());
        categoryModel.put("Animals", Collections.emptyList());
        categoryModel.put("Art", Collections.emptyList());

        return categoryModel;
    }

    public Set<String> getCategoryKey() {
        return categoryModel.keySet();
    }

    public Collection<List<String>> getCategoryValues() {
        return categoryModel.values();
    }
}
