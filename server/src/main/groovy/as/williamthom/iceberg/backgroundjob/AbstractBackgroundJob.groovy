package as.williamthom.iceberg.backgroundjob

abstract class AbstractBackgroundJob<T, K> {

    abstract K performJob(T request)
}