package com.monaschinas.readr.platform.publishing.mapping;

import com.monaschinas.readr.platform.publishing.domain.model.Book;
import com.monaschinas.readr.platform.publishing.resource.BookResource;
import com.monaschinas.readr.platform.publishing.resource.CreateBookResource;
import com.monaschinas.readr.platform.publishing.resource.UpdateBookResource;
import com.monaschinas.readr.platform.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class BookMapper {
    @Autowired
    EnhancedModelMapper mapper;

    public BookResource toResource(Book model){ return  mapper.map(model, BookResource.class); }

    public Page<BookResource> modelListPage(List<Book> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, BookResource.class), pageable, modelList.size());
    }

    public Book toModel(CreateBookResource resource){ return mapper.map(resource, Book.class); }

    public Book toModel(UpdateBookResource resource){ return mapper.map(resource, Book.class); }
}
