package azmain.github.io.util;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class ListResultBuilder {

    public static <K,V> List<V> listBuild(List<K> items, ResultMapper<K,V> mapper){
        return items.stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    public static <K,V> PagedResult<V> pageListBuild(Page<K> page, ResultMapper<K,V> mapper){
        return new PagedResult<V>(
                page.getContent().stream()
                .map((item)-> mapper.map(item))
                .collect(Collectors.toList()),
                page.getSize(),
                page.getNumber(),
                page.getTotalPages());
    }

}
