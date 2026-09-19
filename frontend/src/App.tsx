import { useEffect, useState } from 'react'
import { request } from 'graphql-request'
import { graphql } from './gql'
import type { DocumentType } from './gql'

const GetBooksDocument = graphql(`
  query GetBooks {
    getAllBooks {
      id
      title
      isbn
      publishedYear
      authors {
        id
        firstName
        lastName
      }
    }
  }
`)

type GetBooksData = DocumentType<typeof GetBooksDocument>
const graphqlEndpoint = new URL('/graphql', window.location.origin).toString()

function App() {
  const [data, setData] = useState<GetBooksData | null>(null)
  const [error, setError] = useState<string | null>(null)
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    request(graphqlEndpoint, GetBooksDocument)
      .then(setData)
      .catch((error: unknown) => {
        setError(error instanceof Error ? error.message : 'Errore nel recupero dei dati')
      })
      .finally(() => setLoading(false))
  }, [])

  if (loading) return <main>Caricamento...</main>
  if (error) return <main>Errore: {error}</main>

  return (
    <main>
      <h1>Libri</h1>
      <ul>
        {data?.getAllBooks.map((book) => (
          <li key={book.id}>
            <strong>{book.title}</strong> ({book.publishedYear}) - {book.isbn}
            <ul>
              {book.authors.map((author) => (
                <li key={author.id}>
                  {author.firstName} {author.lastName}
                </li>
              ))}
            </ul>
          </li>
        ))}
      </ul>
    </main>
  )
}

export default App
