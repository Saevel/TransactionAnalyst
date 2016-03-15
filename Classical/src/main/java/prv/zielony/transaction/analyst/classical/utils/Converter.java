package prv.zielony.transaction.analyst.classical.utils;

/**
 * Created by zielony on 27.02.16.
 */
public interface Converter<SourceType, TargetType> {

    TargetType convert(SourceType source);
}
