package as.williamthom.iceberg.utils

import com.google.gson.*
import groovy.util.logging.Slf4j
import io.micronaut.core.util.StringUtils

import java.lang.reflect.Type
import java.text.SimpleDateFormat

@Slf4j
class JSONUtils {

    static Gson newGson(GsonBuilderModifier hook) {

        JSONUtilsContext context = new JSONUtilsContext();
        GsonBuilder builder = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateSerializer(context))
                .registerTypeAdapter(Double.class, new DoubleSerializer(context))

        if (hook != null) {
            hook.modifyBuilder(builder)
        }

        Gson gson = builder.create()
        context.setGson(gson)

        return gson;
    }

    static String toJson(Object object) {
        return newGson(null).toJson(object)
    }

    static String toJson(Object object, GsonBuilderModifier hook) {
        return newGson(hook).toJson(object)
    }

    static <T> T fromJson(String json, Class<T> clazz) {
        return newGson(null).fromJson(json, clazz)
    }

    static <T> T fromJson(String json, Class<T> clazz, GsonBuilderModifier hook) {
        return newGson(hook).fromJson(json, clazz);
    }

    static String toJsonSnakeCase(Object object) {
        return newGson(new SnakeCaseModifier()).toJson(object)
    }

    static <T> T fromJsonSnakeCase(String json, Class<T> clazz) {
        return newGson(new SnakeCaseModifier()).fromJson(json, clazz)
    }

    interface GsonBuilderModifier {
        void modifyBuilder(GsonBuilder builder)
    }

    private static class SnakeCaseModifier implements GsonBuilderModifier {
        @Override
        void modifyBuilder(GsonBuilder builder) {
            builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        }
    }

    private static abstract class AbstractButtonwoodDeserializer {

        JSONUtilsContext _jsonUtilsContext

        AbstractButtonwoodDeserializer(JSONUtilsContext context) {
            _jsonUtilsContext = context
        }

    }

    private static class DoubleSerializer extends AbstractButtonwoodDeserializer implements JsonSerializer<Double> {

        DoubleSerializer(JSONUtilsContext context) {
            super(context);
        }

        @Override
        JsonElement serialize(Double src, Type type, JsonSerializationContext jsonSerializationContext) {
            if (src == src.longValue()) {
                return new JsonPrimitive(src.longValue());
            }
            return new JsonPrimitive(src);
        }
    }

    private static class DateSerializer extends AbstractButtonwoodDeserializer implements JsonDeserializer<Date>, JsonSerializer<Date> {

        private static List<String> formats = [
            "yyyy-MM-dd'T'HH:mm:ssX",
            "yyyy-MM-dd'T'HH:mm:ssz"
        ]

        DateSerializer(JSONUtilsContext context) {
            super(context);
        }

        @Override
        Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            String value = json.getAsString();
            if (!StringUtils.isEmpty(value)) {
                for (String format : formats) {
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat(format);
                        Date result = sdf.parse(json.getAsString());
                        return result;
                    } catch (Exception ex) {
                        log.trace(String.format("An error occurred trying format '%s' on value '%s'", format, value));
                    }
                }
                log.warn(String.format("Could not parse date '%s' with currently recognized formats", value));
            }
            return null;
        }

        @Override
        JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
            SimpleDateFormat sdf = new SimpleDateFormat(formats[0]);
            return new JsonPrimitive(sdf.format(src));
        }

    }

    private static class JSONUtilsContext {

        private Gson _gson;

        void setGson(Gson gson) {
            _gson = gson;
        }

        Gson getGson() {
            return _gson;
        }
    }
}
