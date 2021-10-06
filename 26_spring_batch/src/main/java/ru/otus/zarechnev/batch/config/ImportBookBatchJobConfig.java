package ru.otus.zarechnev.batch.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import ru.otus.zarechnev.batch.domain.BookDocument;
import ru.otus.zarechnev.batch.domain.BookEntity;
import ru.otus.zarechnev.batch.repository.AuthorDocumentRepositoryMongo;
import ru.otus.zarechnev.batch.service.BookEntityToDocumentConverter;

import javax.persistence.EntityManagerFactory;

@Configuration
public class ImportBookBatchJobConfig {
    private static final int CHUNK_SIZE = 5;
    public static final String IMPORT_BOOK_JOB_NAME = "importBookJob";

    private final Logger logger = LoggerFactory.getLogger("Batch");

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private AuthorDocumentRepositoryMongo authorDocumentRepositoryMongo;

    @StepScope
    @Bean
    public JpaPagingItemReader<BookEntity> bookReader(EntityManagerFactory entityManagerFactory) {
        return new JpaPagingItemReaderBuilder<BookEntity>()
                .name("bookItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("select b from BookEntity b join fetch b.author")
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<BookEntity, BookDocument> bookProcessor(BookEntityToDocumentConverter
                                                                     bookEntityToDocumentConverter) {
        return bookEntityToDocumentConverter::toDocument;
    }

    @StepScope
    @Bean
    public MongoItemWriter<BookDocument> bookWriter(MongoOperations mongoOperations) {
        return new MongoItemWriterBuilder<BookDocument>()
                .template(mongoOperations)
                .build();
    }

    @Bean
    public Step transformBookStep(
            ItemReader<BookEntity> reader,
            ItemProcessor<BookEntity, BookDocument> itemProcessor,
            ItemWriter<BookDocument> writer
    ) {
        return stepBuilderFactory.get("step1")
                .<BookEntity, BookDocument>chunk(CHUNK_SIZE)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job importBookJob(Step transformBookStep) {
        return jobBuilderFactory.get(IMPORT_BOOK_JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .flow(transformBookStep)
                .end()
                .build();
    }
}
