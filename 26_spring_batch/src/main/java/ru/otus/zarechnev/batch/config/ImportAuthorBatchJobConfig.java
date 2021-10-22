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
import ru.otus.zarechnev.batch.domain.AuthorDocument;
import ru.otus.zarechnev.batch.domain.AuthorEntity;
import ru.otus.zarechnev.batch.repository.AuthorDocumentRepositoryMongo;
import ru.otus.zarechnev.batch.service.AuthorEntityToDocumentConverter;

import javax.persistence.EntityManagerFactory;

@Configuration
public class ImportAuthorBatchJobConfig {
    private static final int CHUNK_SIZE = 5;
    public static final String IMPORT_AUTHOR_JOB_NAME = "importAuthorJob";

    private final Logger logger = LoggerFactory.getLogger("Batch");

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private AuthorDocumentRepositoryMongo authorDocumentRepositoryMongo;

    @StepScope
    @Bean
    public JpaPagingItemReader<AuthorEntity> authorReader(EntityManagerFactory entityManagerFactory) {
        return new JpaPagingItemReaderBuilder<AuthorEntity>()
                .name("authorItemReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("select a from AuthorEntity a")
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<AuthorEntity, AuthorDocument> authorProcessor(AuthorEntityToDocumentConverter
                                                                         authorEntityToDocumentConverter) {
        return authorEntityToDocumentConverter::toDocument;
    }

    @StepScope
    @Bean
    public MongoItemWriter<AuthorDocument> authorWriter(MongoOperations mongoOperations) {
        return new MongoItemWriterBuilder<AuthorDocument>()
                .template(mongoOperations)
                .build();
    }

    @Bean
    public Step transformAuthorStep(
            ItemReader<AuthorEntity> reader,
            ItemProcessor<AuthorEntity, AuthorDocument> itemProcessor,
            ItemWriter<AuthorDocument> writer
    ) {
        return stepBuilderFactory.get("step2")
                .<AuthorEntity, AuthorDocument>chunk(CHUNK_SIZE)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job importAuthorJob(Step transformAuthorStep) {
        return jobBuilderFactory.get(IMPORT_AUTHOR_JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .flow(transformAuthorStep)
                .end()
                .build();
    }
}
