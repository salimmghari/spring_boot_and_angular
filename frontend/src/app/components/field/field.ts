export default interface IField {
    label: string;
    type: string;
    placeholder: string;
    onChange: (event: Event) => void;
}
