import type { CodegenConfig } from '@graphql-codegen/cli'

// where the chema is in the backend
const config: CodegenConfig = {
    schema: 'http://localhost:8081/graphql',

    documents: [
        'src/**/*.{ts,tsx}',
        '!src/gql/**/*'
    ],

    ignoreNoDocuments: true,

    // where to put the typescript code generated
    generates: {
        './src/gql/': {
            preset: 'client',
            config: {
                useTypeImports: true
            }
        }
    }
}

export default config
