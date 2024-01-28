package com.tts.techtalentblog.blogpost;
 
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BlogPostController {
	
	@Autowired
	private BlogPostRepository blogPostRepository;

	@GetMapping(path="/")
	public String index(BlogPost blogPost, Model model) {
		
		
		List<BlogPost> posts = blogPostRepository.findAll();
		//Iterator<BlogPost> iterator=iterable.iterator();
		//List<BlogPost> blogPostList = new ArrayList<>();
		//while(iterator.hasNext()) {
			
		//}
		model.addAttribute("posts", posts);
		model.addAttribute("blogPost", blogPost);
		
		return "blogpost/index";
	}
	
	@GetMapping(path="/blogposts/new")
	public String newBlog(Model model) {
		
		BlogPost blogPost = new BlogPost();
		
		model.addAttribute("blogPost", blogPost);
		return "blogpost/new";
	}
	@PostMapping(path="/blogposts")
	public String addNewBlogPost(BlogPost blogPost, Model model) {
		BlogPost savedPost = blogPostRepository.save(blogPost);
		
		model.addAttribute("blogPost", blogPost );

		
		return "blogpost/result";
	}
	
	@GetMapping(path="/blogposts/{id}")
	public String editPostWithId(@PathVariable Long id, Model model) {
		Optional<BlogPost> blogPostContainer=blogPostRepository.findById(id);
		
		if(blogPostContainer.isPresent()) {
			BlogPost actualPost = blogPostContainer.get();
			model.addAttribute("blogPost", actualPost);
		}
		return "blogpost/edit";
	}
	
	@PostMapping(path="/blogposts/update/{id}")
	public String updateExistingPost(@PathVariable Long id, Model model, BlogPost blogPost ) {
		Optional<BlogPost> blogPostContainer=blogPostRepository.findById(id);
		if(blogPostContainer.isPresent()) {
			
			BlogPost actualPost = blogPostContainer.get();
			actualPost.setTitle(blogPost.getTitle());
			actualPost.setAuthor(blogPost.getAuthor());
			actualPost.setBlogEntry(blogPost.getBlogEntry());
			
			blogPostRepository.save(actualPost);
			model.addAttribute("blogPost", actualPost);
		}
		return "blogpost/result";

	}
	
	@GetMapping(path="/blogposts/delete/{id}")
	public String deletePostById(@PathVariable Long id) {
		blogPostRepository.deleteById(id);
		
		return "blogpost/delete";
		
	}
}
