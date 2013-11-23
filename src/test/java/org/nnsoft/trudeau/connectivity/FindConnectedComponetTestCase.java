package org.nnsoft.trudeau.connectivity;

/*
 *   Copyright 2013 The Trudeau Project
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.nnsoft.trudeau.connectivity.ConnectivitySolver.findConnectedComponent;
import static org.nnsoft.trudeau.connector.GraphPopulator.populate;

import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.nnsoft.trudeau.api.Graph;
import org.nnsoft.trudeau.connector.AbstractGraphConnection;
import org.nnsoft.trudeau.connector.GraphConnection;
import org.nnsoft.trudeau.inmemory.UndirectedMutableGraph;
import org.nnsoft.trudeau.inmemory.labeled.BaseLabeledEdge;
import org.nnsoft.trudeau.inmemory.labeled.BaseLabeledVertex;
import org.nnsoft.trudeau.inmemory.labeled.BaseLabeledWeightedEdge;

/**
 */
public final class FindConnectedComponetTestCase
{

    @Test( expected = NullPointerException.class )
    public void verifyNullGraph()
    {
        findConnectedComponent( (Graph<BaseLabeledVertex, BaseLabeledWeightedEdge<Double>>) null ).includingAllVertices().applyingMinimumSpanningTreeAlgorithm();
    }

    @Test
    public void verifyEmptyGraph()
    {
        UndirectedMutableGraph<BaseLabeledVertex, BaseLabeledEdge> graph =
            new UndirectedMutableGraph<BaseLabeledVertex, BaseLabeledEdge>();

        Collection<List<BaseLabeledVertex>> c =
            findConnectedComponent( graph ).includingAllVertices().applyingMinimumSpanningTreeAlgorithm();
        assertNotNull( c );
        assertEquals( 0, c.size() );
    }

    @Test
    public void verifyNullVerticesGraph()
    {
        UndirectedMutableGraph<BaseLabeledVertex, BaseLabeledEdge> graph =
        newUndirectedMutableGraph( new AbstractGraphConnection<BaseLabeledVertex, BaseLabeledEdge>()
            {

                public void connect()
                {
                    addVertex( new BaseLabeledVertex( "B" ) );
                    addVertex( new BaseLabeledVertex( "C" ) );
                }

            } );
        Collection<List<BaseLabeledVertex>> c =
            findConnectedComponent( graph ).includingVertices().applyingMinimumSpanningTreeAlgorithm();
        assertNotNull( c );
        assertEquals( 0, c.size() );
    }

    @Test
    public void verifyConnectedComponents()
    {
        final BaseLabeledVertex a = new BaseLabeledVertex( "A" );

        UndirectedMutableGraph<BaseLabeledVertex, BaseLabeledEdge> graph =
        newUndirectedMutableGraph( new AbstractGraphConnection<BaseLabeledVertex, BaseLabeledEdge>()
        {

            public void connect()
            {
                addVertex( a );
                addVertex( new BaseLabeledVertex( "B" ) );
                addVertex( new BaseLabeledVertex( "C" ) );
                addVertex( new BaseLabeledVertex( "D" ) );
                addVertex( new BaseLabeledVertex( "E" ) );
                addVertex( new BaseLabeledVertex( "F" ) );
                addVertex( new BaseLabeledVertex( "G" ) );
                addVertex( new BaseLabeledVertex( "H" ) );
            }

        } );

        Collection<List<BaseLabeledVertex>> c = findConnectedComponent( graph ).includingAllVertices().applyingMinimumSpanningTreeAlgorithm();

        assertNotNull( c );
        assertFalse( c.isEmpty() );
        assertEquals( 8, c.size() );
    }

    @Test
    public void verifyConnectedComponents2()
    {
        final BaseLabeledVertex a = new BaseLabeledVertex( "A" );

        UndirectedMutableGraph<BaseLabeledVertex, BaseLabeledEdge> graph =
        newUndirectedMutableGraph( new AbstractGraphConnection<BaseLabeledVertex, BaseLabeledEdge>()
        {

            public void connect()
            {
                addVertex( a );
                BaseLabeledVertex b = addVertex( new BaseLabeledVertex( "B" ) );
                BaseLabeledVertex c = addVertex( new BaseLabeledVertex( "C" ) );
                BaseLabeledVertex d = addVertex( new BaseLabeledVertex( "D" ) );
                BaseLabeledVertex e = addVertex( new BaseLabeledVertex( "E" ) );
                BaseLabeledVertex f = addVertex( new BaseLabeledVertex( "F" ) );
                BaseLabeledVertex g = addVertex( new BaseLabeledVertex( "G" ) );
                BaseLabeledVertex h = addVertex( new BaseLabeledVertex( "H" ) );

                addEdge( new BaseLabeledEdge( "A -> F" ) ).from( a ).to( f );
                addEdge( new BaseLabeledEdge( "A -> B" ) ).from( a ).to( b );
                addEdge( new BaseLabeledEdge( "B -> F" ) ).from( b ).to( f );
                addEdge( new BaseLabeledEdge( "C -> G" ) ).from( c ).to( g );
                addEdge( new BaseLabeledEdge( "D -> G" ) ).from( d ).to( g );
                addEdge( new BaseLabeledEdge( "E -> F" ) ).from( e ).to( f );
                addEdge( new BaseLabeledEdge( "H -> C" ) ).from( h ).to( c );
            }

        } );

        Collection<List<BaseLabeledVertex>> c = findConnectedComponent( graph ).includingAllVertices().applyingMinimumSpanningTreeAlgorithm();

        assertNotNull( c );
        assertFalse( c.isEmpty() );
        assertEquals( 2, c.size() );
    }

    @Test
    public void verifyConnectedComponents3()
    {
        final BaseLabeledVertex a = new BaseLabeledVertex( "A" );

        UndirectedMutableGraph<BaseLabeledVertex, BaseLabeledEdge> graph =
        newUndirectedMutableGraph( new AbstractGraphConnection<BaseLabeledVertex, BaseLabeledEdge>()
        {

            public void connect()
            {
                addVertex( a );
                BaseLabeledVertex b = addVertex( new BaseLabeledVertex( "B" ) );
                BaseLabeledVertex c = addVertex( new BaseLabeledVertex( "C" ) );

                addEdge( new BaseLabeledEdge( "A -> B" ) ).from( a ).to( b );
                addEdge( new BaseLabeledEdge( "B -> C" ) ).from( b ).to( c );
                addEdge( new BaseLabeledEdge( "C -> A" ) ).from( c ).to( a );
            }

        } );

        Collection<List<BaseLabeledVertex>> c = findConnectedComponent( graph ).includingAllVertices().applyingMinimumSpanningTreeAlgorithm();

        assertNotNull( c );
        assertFalse( c.isEmpty() );
        assertEquals( 1, c.size() );
    }

    @Test
    public void verifyConnectedComponentsIncludingVertices()
    {
        final BaseLabeledVertex a = new BaseLabeledVertex( "A" );

        UndirectedMutableGraph<BaseLabeledVertex, BaseLabeledEdge> graph =
        newUndirectedMutableGraph( new AbstractGraphConnection<BaseLabeledVertex, BaseLabeledEdge>()
        {

            public void connect()
            {
                addVertex( a );
                BaseLabeledVertex b = addVertex( new BaseLabeledVertex( "B" ) );
                BaseLabeledVertex c = addVertex( new BaseLabeledVertex( "C" ) );
                BaseLabeledVertex d = addVertex( new BaseLabeledVertex( "D" ) );
                BaseLabeledVertex e = addVertex( new BaseLabeledVertex( "E" ) );
                BaseLabeledVertex f = addVertex( new BaseLabeledVertex( "F" ) );
                BaseLabeledVertex g = addVertex( new BaseLabeledVertex( "G" ) );
                BaseLabeledVertex h = addVertex( new BaseLabeledVertex( "H" ) );

                addEdge( new BaseLabeledEdge( "A -> F" ) ).from( a ).to( f );
                addEdge( new BaseLabeledEdge( "A -> B" ) ).from( a ).to( b );
                addEdge( new BaseLabeledEdge( "B -> F" ) ).from( b ).to( f );
                addEdge( new BaseLabeledEdge( "C -> G" ) ).from( c ).to( g );
                addEdge( new BaseLabeledEdge( "D -> G" ) ).from( d ).to( g );
                addEdge( new BaseLabeledEdge( "E -> F" ) ).from( e ).to( f );
                addEdge( new BaseLabeledEdge( "H -> C" ) ).from( h ).to( c );
            }

        } );

        Collection<List<BaseLabeledVertex>> coll = findConnectedComponent( graph ).includingVertices( a ).applyingMinimumSpanningTreeAlgorithm();

        assertNotNull( coll );
        assertFalse( coll.isEmpty() );
        assertEquals( 1, coll.size() );
    }

    @Test
    public void verifyConnectedComponentsIncludingVertices2()
    {
        final BaseLabeledVertex a = new BaseLabeledVertex( "A" );
        final BaseLabeledVertex e = new BaseLabeledVertex( "E" );

        UndirectedMutableGraph<BaseLabeledVertex, BaseLabeledEdge> graph =
        newUndirectedMutableGraph( new AbstractGraphConnection<BaseLabeledVertex, BaseLabeledEdge>()
        {

            public void connect()
            {
                addVertex( a );
                addVertex( new BaseLabeledVertex( "B" ) );
                addVertex( new BaseLabeledVertex( "C" ) );
                addVertex( new BaseLabeledVertex( "D" ) );
                addVertex( e );
                addVertex( new BaseLabeledVertex( "F" ) );
                addVertex( new BaseLabeledVertex( "G" ) );
                addVertex( new BaseLabeledVertex( "H" ) );

            }

        } );

        Collection<List<BaseLabeledVertex>> coll = findConnectedComponent( graph ).includingVertices( a, e ).applyingMinimumSpanningTreeAlgorithm();

        assertNotNull( coll );
        assertFalse( coll.isEmpty() );
        assertEquals( 2, coll.size() );
    }

    private UndirectedMutableGraph<BaseLabeledVertex, BaseLabeledEdge> newUndirectedMutableGraph( GraphConnection<BaseLabeledVertex, BaseLabeledEdge> graphConnection )
    {
        return populate( new UndirectedMutableGraph<BaseLabeledVertex, BaseLabeledEdge>() ).withConnections( graphConnection );
    }

}
