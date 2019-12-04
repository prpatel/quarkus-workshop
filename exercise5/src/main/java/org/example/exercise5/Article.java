package org.example.exercise5;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Article extends PanacheEntity {

  @NotBlank
  public String title;
  public String subtitle;
  public String linktotweet;
  public String coverImage;
  public int likes;
  @NotBlank
  public String asciidocsource;
  @CreationTimestamp
  public LocalDateTime created;
  @UpdateTimestamp
  public LocalDateTime modified;

  @ManyToMany(cascade = { CascadeType.PERSIST,
    CascadeType.MERGE }, fetch = FetchType.EAGER)
  @JoinTable(
    name = "article_tag",
    joinColumns = { @JoinColumn(name = "article_id") },
    inverseJoinColumns = { @JoinColumn(name = "tag_id") }
  )
  public Set<Tag> tags = new HashSet<>();

//  public void addTag(Tag newTag) {
//    tags.add(newTag);
//    newTag.articles.add(this);
//  }
//
//  public void removeTag(Tag aTag) {
//    tags.remove(aTag);
//    aTag.articles.remove(this);
//  }
//
//  @Transient
//  public String getTagList() {
//    return tags.toString();
//  }

}
