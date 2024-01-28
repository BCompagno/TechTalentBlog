package com.tts.techtalentblog.blogpost;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends CrudRepository<BlogPost, Long>{
	
	List<BlogPost> findAll();
	
}
