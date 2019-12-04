package org.example.exercise3;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Tag extends PanacheEntity {

  @Column(nullable = false, unique = true)
  public String name;

  @Override
  public String toString() {
    return name;
  }
}
