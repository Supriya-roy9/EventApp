import { fireEvent, render,screen } from "@testing-library/react"
import Header from "./Header"

describe("Basic rendering",()=>{
    it('Should render the header component',()=>{
        render(<Header/>);
        
        expect(screen.getByLabelText("header")).toBeInTheDocument();
        expect(screen.getByText("My Cinemas")).toBeInTheDocument();
        expect(screen.getByRole("link")).toBeInTheDocument();
    });

    it('Should redirect to the main page when the icon is clicked',()=>{
        render(<Header/>);
        const linkElement=screen.getByRole('link');

        fireEvent.click(linkElement);

        expect(linkElement).toHaveAttribute('href','/');

    });

})