package as.williamthom.iceberg.utils.polling

import as.williamthom.iceberg.utils.JSONUtils
import groovy.util.logging.Slf4j
import java.time.LocalDate

@Slf4j
class PollFileHandler {

    private final static String WORKING_DIR = '/opt/iceberg/statuses'

    static UrlEntryPollResults getPollResult(final String name) {
        confirmOrCreateParentDir(WORKING_DIR)

        def folder = new File(WORKING_DIR)

        UrlEntryPollResults results = null

        folder.eachFile { File file ->
            def tokens = file.name.tokenize('+')
            if (tokens.first() == name) {
                results = JSONUtils.fromJson(file.text, UrlEntryPollResults)
            }
        }

        return results
    }

    static List<UrlEntryPollResults> getAllPollResults() {
        confirmOrCreateParentDir(WORKING_DIR)

        def folder = new File(WORKING_DIR)

        List<UrlEntryPollResults> results = []
        folder.eachFile { File file ->
            if (!file.name.contains('+')) return

            try {
                def result = JSONUtils.fromJson(file.text, UrlEntryPollResults)
                results << result
            } catch (Exception ex) {
                log.error("Cannot parse ${file.name} into UrlEntryPollResult", ex)
            }
        }

        return results
    }

    static UrlEntryPollResults getPollResultFromFile(final String addr, final String name) {
        confirmOrCreateParentDir(WORKING_DIR)

        String fileName = WORKING_DIR + "/${name}+${LocalDate.now().toString()}".toString()

        File file = new File(fileName)
        if (file.exists()) {
            try {
                return JSONUtils.fromJson(file.text, UrlEntryPollResults)
            } catch (Exception ex) {
                log.error("Error reading poll result file", ex)
                return new UrlEntryPollResults(addr, name, fileName)
            }
        }

        return new UrlEntryPollResults(addr, name, fileName)
    }

    static void savePollResult(UrlEntryPollResults result) {
        File file = new File(result.fileName)
        file.write(JSONUtils.toJson(result))
    }

    private static void confirmOrCreateParentDir(String workingDir) {
        File dir = new File(workingDir)
        if (!dir.exists()) {
            dir.mkdirs()
        }
    }

}
