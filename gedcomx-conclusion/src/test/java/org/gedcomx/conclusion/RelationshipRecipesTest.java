package org.gedcomx.conclusion;

import org.gedcomx.common.*;
import org.gedcomx.test.RecipeTest;
import org.gedcomx.test.Snippet;
import org.gedcomx.types.FactType;
import org.gedcomx.types.RelationshipType;
import org.gedcomx.types.ResourceType;
import org.testng.annotations.Test;

import java.util.List;

import static org.gedcomx.rt.SerializationUtil.processThroughJson;
import static org.gedcomx.rt.SerializationUtil.processThroughXml;
import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Ryan Heaton
 */
@Test
public class RelationshipRecipesTest extends RecipeTest {

  public void testRelationship() throws Exception {
    createRecipe("Simple Relationship")
      .withDescription("Simple example for a relationship.")
      .applicableTo(Relationship.class);

    Relationship relationship = createTestRelationship();
    Snippet snippet = new Snippet();
    relationship = processThroughXml(relationship, snippet);
    relationship = processThroughJson(relationship, snippet);
    assertTestRelationship(relationship);
  }

  private Relationship createTestRelationship() {
    Relationship relationship = new Relationship();
    relationship.setId("CCC-CCCC");
    relationship.setKnownType(RelationshipType.Couple);

    relationship.setAttribution(new Attribution());
    relationship.getAttribution().setProofStatement("(proof statement here)");
    ResourceReference contributor = new ResourceReference();
    contributor.setResource(URI.create("https://familysearch.org/platform/contributors/BCD-FGHJ"));
    relationship.getAttribution().setContributor(contributor);
    Fact fact = new Fact();
    fact.setId("123");
    fact.setKnownType(FactType.Marriage);

    fact.setAttribution(new Attribution());
    fact.getAttribution().setContributor(new ResourceReference());
    fact.getAttribution().getContributor().setResource(URI.create("https://familysearch.org/platform/contributors/HHH-HHHH"));
    fact.setDate(new Date());
    fact.getDate().setOriginal("January 6, 1759");
    FormalValue normalized = new FormalValue();
    normalized.setText("1759-01-06");
    normalized.setDatatype(URI.create("http://www.w3.org/2001/XMLSchema#date"));
    fact.getDate().setFormal(normalized);

    relationship.addFact(fact);
    relationship.setPerson1(new ResourceReference());
    relationship.getPerson1().setResource(URI.create("https://familysearch.org/platform/persons/DDD-DDDD"));
    relationship.setPerson2(new ResourceReference());
    relationship.getPerson2().setResource(URI.create("https://familysearch.org/platform/persons/FFF-FFFF"));
    SourceReference sourceReference = new SourceReference();
    sourceReference.setId("5678");
    sourceReference.setResource(URI.create("http://en.wikipedia.org/wiki/George_washington"));
    sourceReference.setKnownType(ResourceType.Text);
    relationship.addSource(sourceReference);
    return relationship;
  }

  private void assertTestRelationship(Relationship relationship) {
    //todo
  }

}
